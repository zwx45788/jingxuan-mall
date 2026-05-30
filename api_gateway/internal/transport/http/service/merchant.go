package service

import (
	"context"
	"errors"
	"mime/multipart"
	"shopping/api/shopping"
	"shopping/internal/logger"
	"shopping/internal/pkg/oss"
	"shopping/internal/transport/http/grpc/shoppingclient"
	"shopping/internal/transport/http/request"
)

func RegisterMerchant(username, password, email string) error {

	cli, err := shoppingclient.GetMerchantServiceStub()
	if err != nil {
		logger.Log.Error("grpc连接不可用")
		return err
	}

	resp, err := cli.RegisterMerchant(context.Background(), &shopping.RegisterMerchantRequest{
		Username: username,
		Password: password,
		Email:    email,
	})

	if err != nil {
		return err
	}

	if !resp.Success {
		return errors.New(resp.Message)
	}

	return nil
}

func LoginMerchant(email, password string) (string, error) {
	cli, err := shoppingclient.GetMerchantServiceStub()

	if err != nil {
		return "", err
	}

	resp, err := cli.LoginMerchant(context.Background(), &shopping.LoginMerchantRequest{
		Email:    email,
		Password: password,
	})

	if err != nil {
		return "", err
	}

	if !resp.Success {
		return "", errors.New(resp.Message)
	}

	return resp.Token, nil
}

func CreateProduct(categoryId uint64, title string, mainImage *multipart.FileHeader, totalStock uint64, skus []request.Sku) error {
	cli, err := shoppingclient.GetMerchantServiceStub()

	if err != nil {
		return err
	}

	ossCli, err := oss.GetOssClient()
	if err != nil {
		return err
	}

	path, err := ossCli.UploadFile(mainImage)
	if err != nil {
		return err
	}
	s := make([]*shopping.Sku, len(skus))
	for i := range skus {
		filePath, err := ossCli.UploadFile(skus[i].Image)
		if err != nil {
			return err
		}
		sku := &shopping.Sku{
			Price:    skus[i].Price,
			SpecJson: skus[i].SpecJson,
			Stock:    skus[i].Stock,
			Image:    filePath,
		}
		s = append(s, sku)
	}

	resp, err := cli.CreateProduct(context.Background(), &shopping.CreateProductRequest{
		CategoryId: categoryId,
		Title:      title,
		MainImage:  path,
		TotalStock: totalStock,
		Skus:       s,
	})

	if err != nil {
		return err
	}

	if !resp.Success {
		return errors.New(resp.Message)
	}
	return nil
}

func PublishProduct(productIds []uint64) error {

	cli, err := shoppingclient.GetMerchantServiceStub()
	if err != nil {
		return err
	}

	resp, err := cli.PublishProduct(context.Background(), &shopping.PublishProductRequest{
		ProductIds: productIds,
	})

	if err != nil {
		return err
	}

	if !resp.Success {
		return errors.New(resp.Message)
	}
	return nil
}

func UnPublishProduct(productIds []uint64) error {

	cli, err := shoppingclient.GetMerchantServiceStub()
	if err != nil {
		return err
	}

	resp, err := cli.UnPublishProduct(context.Background(), &shopping.UnPublishProductRequest{
		ProductIds: productIds,
	})

	if err != nil {
		return err

	}

	if !resp.Success {
		return errors.New(resp.Message)
	}
	return nil
}

func CreateShop(merchantId uint64, shopName, description, logo string) error {

	cli, err := shoppingclient.GetMerchantServiceStub()
	if err != nil {
		return err
	}

	resp, err := cli.CreateShop(context.Background(), &shopping.CreateShopRequest{
		MerchantId:  merchantId,
		ShopName:    shopName,
		Description: description,
		Logo:        logo,
	})
	if err != nil {
		return err
	}
	if !resp.Success {
		return errors.New(resp.Message)
	}
	return nil
}
