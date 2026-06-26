package shoppingclient

import (
	"context"
	shoppingpb "shopping/api/shopping"
	"shopping/internal/logger"
	"shopping/internal/transport/http/grpc"
)

var (
	manager             *grpc.ClientConnmanager
	userServiceStub     shoppingpb.UserServiceClient
	merchantServiceStub shoppingpb.MerchantServiceClient
	cartServiceStub     shoppingpb.CartServiceClient
	orderServiceStub    shoppingpb.OrderServiceClient
)

func Init(ctx context.Context, addr string) error {
	manager = grpc.NewClientConnManager(addr)
	if err := manager.Init(ctx); err != nil {
		return err
	}
	manager.Init(ctx)
	userServiceStub = shoppingpb.NewUserServiceClient(manager.GetConn())
	merchantServiceStub = shoppingpb.NewMerchantServiceClient(manager.GetConn())
	cartServiceStub = shoppingpb.NewCartServiceClient(manager.GetConn())
	orderServiceStub = shoppingpb.NewOrderServiceClient(manager.GetConn())

	return nil
}

func GetUserServiceStub() (shoppingpb.UserServiceClient, error) {
	if err := manager.Isready(); err != nil {
		logger.Log.Errorf("gRPC connection is not ready: %v", err)
		return nil, err
	}
	return userServiceStub, nil
}
func GetMerchantServiceStub() (shoppingpb.MerchantServiceClient, error) {
	if err := manager.Isready(); err != nil {
		logger.Log.Errorf("gRPC connection is not ready: %v", err)
		return nil, err
	}
	return merchantServiceStub, nil
}
func GetCartServiceStub() (shoppingpb.CartServiceClient, error) {
	if err := manager.Isready(); err != nil {
		logger.Log.Errorf("gRPC connection is not ready: %v", err)
		return nil, err
	}
	return cartServiceStub, nil
}
func GetOrderServiceStub() (shoppingpb.OrderServiceClient, error) {
	if err := manager.Isready(); err != nil {
		logger.Log.Errorf("gRPC connection is not ready: %v", err)
		return nil, err
	}
	return orderServiceStub, nil
}
func Close() {
	if manager != nil {
		manager.GetConn().Close()
	}
}
