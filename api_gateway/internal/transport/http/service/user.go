package service

import (
	"context"
	"errors"
	shoppingpb "shopping/api/shopping"
	"shopping/internal/transport/http/grpc/shoppingclient"
)

func RegisterUser(username, password, email string) error {
	cli, err := shoppingclient.GetUserServiceStub()
	if err != nil {
		return err
	}
	resp, err := cli.RegisterUser(context.Background(), &shoppingpb.RegisterUserRequest{
		Username: username,
		Password: password,
		Email:    email,
	})
	if err != nil {
		return err
	}
	if !resp.Success {
		return errors.New(resp.Message)
	}

	return nil
}

func LoginUser(email, password string) (string, error) {
	cli, err := shoppingclient.GetUserServiceStub()
	if err != nil {
		return "", err
	}
	resp, err := cli.LoginUser(context.Background(), &shoppingpb.LoginUserRequest{
		Email:    email,
		Password: password,
	})
	if err != nil {
		return "", err
	}
	return resp.Token, nil
}
