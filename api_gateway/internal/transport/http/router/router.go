package router

import (
	"shopping/internal/transport/http/handler"
	"shopping/internal/transport/http/middleware"

	"github.com/gin-gonic/gin"
)

func New() *gin.Engine {
	r := gin.Default()

	r.POST("/user/register", handler.RegisterUser)
	r.POST("/user/login", handler.LoginUser)
	r.POST("/merchant/register", handler.RegisterMerchant)
	r.POST("/merchant/login", handler.LoginMerchant)

	userapi := r.Group("/api", middleware.Auth())
	{
		userapi.GET("/user/info", handler.GetUserInfo)
	}
	return r
}
