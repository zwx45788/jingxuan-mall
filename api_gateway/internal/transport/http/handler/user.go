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

// 获取用户信息
func GetUserInfo(c *gin.Context) {

	userid, ok := getUserID(c)

	if !ok {
		return
	}

	resp, err := service.GetUserInfo(userid)
	if err != nil {
		response.Error(c, err.Error())
		return
	}

	data := gin.H{
		"user_id":  resp.User.UserId,
		"username": resp.User.Username,
		"email":    resp.User.Email,
	}
	response.Success(c, data)
}

// 更新地址
func UpdateAddress(c *gin.Context) {

	userid, ok := getUserID(c)

	if !ok {
		return
	}

	req := &request.UpdateAddressRequest{}
	if err := c.BindJSON(req); err != nil {
		response.BadRequest(c, err.Error())
		return
	}

	resp, err := service.UpdateAddress(userid, req.AddressId, req.AddressInfo)
	if err != nil {
		response.Error(c, err.Error())
		return
	}

	data := gin.H{
		"success": resp.Success,
	}
	response.Success(c, data)

}

func ListAddresses(c *gin.Context) {

	userid, ok := getUserID(c)

	if !ok {
		return
	}

	resp, err := service.ListAddresses(userid)
	if err != nil {
		response.Error(c, err.Error())
		return
	}

	data := gin.H{
		"addresses": resp.Addresses,
	}
	response.Success(c, data)
}

func UploadAvatar(c *gin.Context) {

}
