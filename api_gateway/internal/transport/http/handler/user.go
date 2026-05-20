package handler

import (
	"shopping/internal/transport/http/request"
	"shopping/internal/transport/http/response"
	"shopping/internal/transport/http/service"

	"github.com/gin-gonic/gin"
)

func RegisterUser(c *gin.Context) {
	req := &request.RegisterUserRequest{}
	if err := c.ShouldBindJSON(req); err != nil {
		c.JSON(400, gin.H{"error": err.Error()})
		return
	}
	err := service.RegisterUser(req.Username, req.Password, req.Email)
	if err != nil {
		response.Error(c, err.Error())
		return
	}

	response.Success(c, nil)

}

func LoginUser(c *gin.Context) {

	req := &request.LoginUserRequest{}
	if err := c.ShouldBindJSON(req); err != nil {
		response.BadRequest(c, err.Error())
		return
	}

	token, err := service.LoginUser(req.Email, req.Password)
	if err != nil {
		response.Error(c, err.Error())
		return
	}
	data := gin.H{
		"token": token,
	}

	response.Success(c, data)
}

func GetUserInfo(c *gin.Context) {

}
