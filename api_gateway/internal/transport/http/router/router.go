package router

import (
	"shopping/internal/pkg/jwt"
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
	userapi := r.Group("/api/user", middleware.Auth(jwt.RoleUser))
	{
		userapi.GET("/info", handler.GetUserInfo)
		userapi.GET("/cart/info", handler.GetCartInfo)
		userapi.POST("/cart/add", handler.AddToCart)
		userapi.POST("/cart/delete", handler.DeleteCartItem)

	}

	merchantapi := r.Group("/api/merchant", middleware.Auth((jwt.RoleMerchant)))
	{
		merchantapi.GET("/info", handler.GetMerchantInfo)
		merchantapi.POST("/create/shop", handler.CreateShop)
		merchantapi.POST("/create/product", handler.CreateProduct)
		merchantapi.POST("/publish/product", handler.PublishProduct)

	}
	return r
}
