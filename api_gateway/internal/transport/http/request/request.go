package request

import "mime/multipart"

type UpdateAddressRequest struct {
	AddressId   uint64      `json:"address_id" binding:"required"`
	AddressInfo AddressInfo `json:"address_info" binding:"required"`
}
type AddressInfo struct {
	ReceiverName string `json:"receiver_name" binding:"required"`
	PhoneNumber  string `json:"phone_number" binding:"required"`
	Address      string `json:"address" binding:"required"`
}
type RegisterUserRequest struct {
	Username string `json:"username" binding:"required"`
	Password string `json:"password" binding:"required"`
	Email    string `json:"email" binding:"required,email"`
}
type LoginUserRequest struct {
	Email    string `json:"email" binding:"required,email"`
	Password string `json:"password" binding:"required"`
}
type RegisterMerchantRequest struct {
	Username string `json:"username" binding:"required"`
	Password string `json:"password" binding:"required"`
	Email    string `json:"email" binding:"required,email"`
}
type LoginMerchantRequest struct {
	Email    string `json:"email" binding:"required,email"`
	Password string `json:"password" binding:"required"`
}

type AddToCartRequest struct {
	SkuId    uint64 `json:"sku_id" binding:"required"`
	Quantity uint64 `json:"quantuty" binding:"required"`
}

type RemoveFromCartRequest struct {
	CartItemId uint64 `json:"cart_item_id" binding:"required"`
}

type CreateProductRequest struct {
	CategoryId uint64                `json:"category_id" binding:"required"`
	Title      string                `json:"title" binding:"required"`
	MainImage  *multipart.FileHeader `form:"image" binding:"required"`
	TotalStock uint64                `json:"total_stock" binding:"required"`
	Skus       []Sku                 `json:"skus" binding:"required,dive"`
}

type Sku struct {
	Price    uint64                `json:"price" binding:"required"`
	Stock    uint64                `json:"stock" binding:"required"`
	Image    *multipart.FileHeader `form:"image" binding:"required"`
	SpecJson string                `json:"spec_json" binding:"required"`
}

type PublishProductRequest struct {
	ProductIds []uint64 `json:"product_ids" binding:"required,dive"`
}

type UnPublishProductRequest struct {
	ProductIds []uint64 `json:"product_ids" binding:"required,dive"`
}

type CreateShopRequest struct {
	MerchantId  uint64 `json:"merchant_id" binding:"required"`
	ShopName    string `json:"shop_name" binding:"required"`
	Description string `json:"description" binding:"required"`
	Logo        string `json:"logo" binding:"required"`
}

type CreateOrderRequest struct {
	SkuId    uint64 `json:"sku_id" binding:"required"`
	Quantity uint64 `json:"quantity" binding:"required"`
}
