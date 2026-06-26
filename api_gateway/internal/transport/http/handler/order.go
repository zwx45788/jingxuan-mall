package handler

import (
	"shopping/internal/pkg/redis"
	"shopping/internal/transport/http/request"
	"shopping/internal/transport/http/response"
	"shopping/internal/transport/http/service"
	"strconv"

	"github.com/gin-gonic/gin"
)

func CreateOrder(c *gin.Context) {
	var req request.CreateOrderRequest
	if err := c.ShouldBindJSON(&req); err != nil {
		response.BadRequest(c, err.Error())
		return
	}

	userId, ok := getUserID(c)

	if !ok {
		response.Error(c, "无法获取用户ID")
		return
	}
	luascript := `
	local stock = redis.call("DECRBY", KEYS[1],ARGV[1])
	if stock < 0 then
		redis.call("INCRBY", KEYS[1],ARGV[1])
		return -1
	else
		return stock
	end
	`
	cli, err := redis.GetClient()

	result, err := cli.Eval(c, luascript, []string{"sku:" + strconv.FormatUint(req.SkuId, 10)}, req.Quantity).Int()
	if err != nil {
		response.ServiceUnavailable(c, "无法连接到Redis")
		return
	}
	if result == -1 {
		response.Error(c, "库存不足")
		return
	}
	err = service.CreateOrder(userId, req.SkuId, req.Quantity)
	if err != nil {
		response.Error(c, err.Error())
		return
	}
	response.Success(c, nil)

}
