package handler

import (
	"fmt"
	"shopping/internal/logger"
	"shopping/internal/transport/http/request"
	"shopping/internal/transport/http/response"
	"shopping/internal/transport/http/service"

	"github.com/gin-gonic/gin"
)

func GetCartInfo(c *gin.Context) {

	userid, ok := getUserID(c)

	if !ok {
		return
	}

	resp, err := service.GetCartInfo(userid)

	if err != nil {
		response.Error(c, fmt.Sprintf("获取购物车信息失败,%s", err))
	}

	data := gin.H{
		"items": resp.CartItems,
	}
	response.Success(c, data)

}

func AddToCart(c *gin.Context) {

	req := &request.AddToCartRequest{}
	if err := c.BindJSON(req); err != nil {
		response.BadRequest(c, err.Error())
	}

	userid, ok := getUserID(c)

	if !ok {
		return
	}

	_, err := service.AddToCart(userid, req.SkuId, req.Quantity)

	if err != nil {
		response.Error(c, err.Error())
	}

	response.Success(c, nil)
}

func DeleteCartItem(c *gin.Context) {

	req := &request.RemoveFromCartRequest{}

	if err := c.BindJSON(req); err != nil {
		response.BadRequest(c, err.Error())
	}

	userid, ok := getUserID(c)

	if !ok {
		return
	}

	_, err := service.RemoveFromCart(userid, req.CartItemId)

	if err != nil {
		response.Error(c, err.Error())
	}

	response.Success(c, nil)
}

func getUserID(c *gin.Context) (uint64, bool) {
	val, exist := c.Get("user_id")
	if !exist {
		response.Error(c, "内部错误")
		logger.Log.Error("从context获取userid失败")
		c.Abort()
		return 0, false
	}

	userid, ok := val.(uint64)

	if !ok {
		response.Error(c, "内部错误")
		logger.Log.Error("userid断言失败")
		c.Abort()
		return 0, false
	}

	return userid, true
}
