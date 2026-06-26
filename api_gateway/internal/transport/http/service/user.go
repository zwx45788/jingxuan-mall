package service

import (
	"context"
	"errors"
	shoppingpb "shopping/api/shopping"
	"shopping/internal/transport/http/grpc/shoppingclient"
	"shopping/internal/transport/http/request"
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

func GetUserInfo(userId uint64) (*shoppingpb.GetUserInfoResponse, error) {
	cli, err := shoppingclient.GetUserServiceStub()
	if err != nil {
		return nil, err
	}
	resp, err := cli.GetUserInfo(context.Background(), &shoppingpb.GetUserInfoRequest{
		UserId: userId,
	})
	if err != nil {
		return nil, err
	}
	return resp, nil
}

func UpdateUserInfo(userId uint64, username, email string) error {
	cli, err := shoppingclient.GetUserServiceStub()
	if err != nil {
		return err
	}
	resp, err := cli.UpdateUserInfo(context.Background(), &shoppingpb.UpdateUserInfoRequest{
		UserId:   userId,
		Username: username,
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

func ListAddresses(userId uint64) (*shoppingpb.ListAddressesResponse, error) {
	cli, err := shoppingclient.GetUserServiceStub()
	if err != nil {
		return nil, err
	}

	resp, err := cli.ListAddresses(context.Background(), &shoppingpb.ListAddressesRequest{
		UserId: userId,
	})

	if err != nil {
		return nil, err
	}

	if !resp.Success {
		return nil, errors.New(resp.Message)
	}

	return resp, nil
}

func UpdateAddress(userId, addressId uint64, addressInfo request.AddressInfo) (*shoppingpb.UpdateAddressResponse, error) {
	cli, err := shoppingclient.GetUserServiceStub()
	if err != nil {
		return nil, err
	}

	resp, err := cli.UpdateAddress(context.Background(), &shoppingpb.UpdateAddressRequest{
		UserId:    userId,
		AddressId: addressId,
		AddressInfo: &shoppingpb.AddressInfo{
			PhoneNumber:  addressInfo.PhoneNumber,
			Address:      addressInfo.Address,
			ReceiverName: addressInfo.ReceiverName,
		},
	})

	if err != nil {
		return nil, err
	}

	if !resp.Success {
		return nil, errors.New(resp.Message)
	}

	return resp, nil
}
