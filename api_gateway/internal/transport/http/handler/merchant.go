package handler

import (
	"shopping/internal/transport/http/request"
	"shopping/internal/transport/http/response"
	"shopping/internal/transport/http/service"

	"github.com/gin-gonic/gin"
)

func RegisterMerchant(c *gin.Context) {
	req := &request.RegisterMerchantRequest{}
	if err := c.ShouldBind(&req); err != nil {
		response.Error(c, err.Error())
	}

	err := service.RegisterMerchant(req.Username, req.Password, req.Email)
	if err != nil {
		response.Error(c, err.Error())
		return
	}

	response.Success(c, nil)

}

func LoginMerchant(c *gin.Context) {

	req := &request.LoginMerchantRequest{}
	if err := c.ShouldBind(&req); err != nil {
		response.Error(c, err.Error())
	}

	token, err := service.LoginMerchant(req.Email, req.Password)
	if err != nil {
		response.Error(c, err.Error())
	}

	data := gin.H{
		"token": token,
	}

	response.Success(c, data)
}

// 获取商户信息
func GetMerchantInfo(c *gin.Context) {

}

// 获取店铺信息
func GetShopInfo(c *gin.Context) {

}

// 创建店铺
func CreateShop(c *gin.Context) {

	req := &request.CreateShopRequest{}
	if err := c.ShouldBind(&req); err != nil {
		response.Error(c, err.Error())
		return
	}
	err := service.CreateShop(req.MerchantId, req.ShopName, req.Description, req.Logo)
	if err != nil {
		response.Error(c, err.Error())
		return
	}
	response.Success(c, nil)
}

// 创建商品
func CreateProduct(c *gin.Context) {
	req := &request.CreateProductRequest{}
	if err := c.ShouldBind(&req); err != nil {
		response.Error(c, err.Error())
	}

	err := service.CreateProduct(req.CategoryId, req.Title, req.MainImage, req.TotalStock, req.Skus)
	if err != nil {
		response.Error(c, err.Error())
		return
	}
	response.Success(c, nil)
}

// 修改商品
func UpdateProduct(c *gin.Context) {

}

// 批量上架商品
func PublishProduct(c *gin.Context) {
	req := &request.PublishProductRequest{}
	if err := c.ShouldBind(&req); err != nil {
		response.Error(c, err.Error())
		return
	}

	err := service.PublishProduct(req.ProductIds)
	if err != nil {
		response.Error(c, err.Error())
		return
	}

	response.Success(c, nil)
}

// 批量下架商品
func UnPublishProduct(c *gin.Context) {
	req := &request.UnPublishProductRequest{}
	if err := c.ShouldBind(&req); err != nil {
		response.Error(c, err.Error())
		return
	}

	err := service.UnPublishProduct(req.ProductIds)
	if err != nil {
		response.Error(c, err.Error())
		return
	}

	response.Success(c, nil)
}
