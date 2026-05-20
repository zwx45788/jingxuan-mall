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
