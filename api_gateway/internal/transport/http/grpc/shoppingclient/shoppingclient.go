package shoppingclient

import (
	"context"
	shoppingpb "shopping/api/shopping"
	"shopping/internal/logger"
	"shopping/internal/transport/http/grpc"
)

var (
	manager *grpc.ClientConnmanager
	stub    shoppingpb.UserserviceClient
)

func Init(ctx context.Context, addr string) error {
	manager = grpc.NewClientConnManager(addr)
	if err := manager.Init(ctx); err != nil {
		return err
	}
	manager.Init(ctx)
	stub = shoppingpb.NewUserserviceClient(manager.GetConn())
	return nil
}

func Stub() (shoppingpb.UserserviceClient, error) {
	if err := manager.Isready(); err != nil {
		logger.Log.Errorf("gRPC connection is not ready: %v", err)
		return nil, err
	}
	return stub, nil
}

func Close() {
	if manager != nil {
		manager.GetConn().Close()
	}
}
