package service

import (
	"context"
	"errors"
	shoppingpb "shopping/api/shopping"
	"shopping/internal/transport/http/grpc/shoppingclient"
)

func GetCartInfo(userid uint64) (*shoppingpb.GetCartInfoResponse, error) {
	cli, err := shoppingclient.GetCartServiceStub()
	if err != nil {
		return nil, err
	}

	resp, err := cli.GetCartInfo(context.Background(), &shoppingpb.GetCartInfoRequest{
		UserId: userid,
	})

	if err != nil {
		return nil, err
	}

	if !resp.Success {
		return nil, errors.New(resp.Message)
	}

	return resp, nil
}

func AddToCart(userid uint64, skuid uint64, quantity uint64) (*shoppingpb.AddToCartResponse, error) {
	cli, err := shoppingclient.GetCartServiceStub()

	if err != nil {
		return nil, err
	}

	resp, err := cli.AddToCart(context.Background(), &shoppingpb.AddToCartRequest{
		UserId:   userid,
		SkuId:    skuid,
		Quantity: quantity,
	})

	if err != nil {
		return nil, err
	}

	if !resp.Success {
		return nil, errors.New(resp.Message)
	}

	return resp, nil
}

func RemoveFromCart(userid uint64, cartItemId uint64) (*shoppingpb.RemoveFromCartResponse, error) {
	cli, err := shoppingclient.GetCartServiceStub()
	if err != nil {
		return nil, err
	}
	resp, err := cli.RemoveFromCart(context.Background(), &shoppingpb.RemoveFromCartRequest{
		UserId:     userid,
		CartItemId: cartItemId,
	})

	if err != nil {
		return nil, err
	}

	if !resp.Success {
		return nil, errors.New(resp.Message)
	}

	return resp, nil
}
