package service

import (
	"context"
	"errors"
	"shopping/api/shopping"
	"shopping/internal/transport/http/grpc/shoppingclient"
)

func CreateOrder(userId, skuId, quantity uint64) error {

	orderClient, err := shoppingclient.GetOrderServiceStub()
	if err != nil {
		return err
	}
	resp, err := orderClient.CreateOrder(context.Background(), &shopping.CreateOrderRequest{
		UserId:   userId,
		SkuId:    skuId,
		Quantity: quantity,
	})

	if err != nil {
		return err
	}

	if !resp.Success {
		return errors.New(resp.Message)
	}
	return nil
}
