package grpc

import (
	"context"
	"fmt"
	"sync"

	"google.golang.org/grpc"
	"google.golang.org/grpc/connectivity"
	"google.golang.org/grpc/credentials/insecure"
)

type ClientConnmanager struct {
	mu   sync.Mutex
	conn *grpc.ClientConn
	addr string
}

func NewClientConnManager(addr string) *ClientConnmanager {
	return &ClientConnmanager{
		addr: addr,
	}
}

func (m *ClientConnmanager) Init(ctx context.Context) error {
	c, err := grpc.NewClient(m.addr,
		grpc.WithTransportCredentials(insecure.NewCredentials()),
		grpc.WithDefaultCallOptions(grpc.WaitForReady(true)),
	)
	if err != nil {
		return err
	}

	m.conn = c

	go m.monitorState(ctx)

	return nil
}

func (m *ClientConnmanager) monitorState(ctx context.Context) {
	for {
		state := m.conn.GetState()
		if state == connectivity.Shutdown {
			m.mu.Lock()
			m.conn.Close()
			m.conn = nil
			m.mu.Unlock()
			return
		}
		m.conn.WaitForStateChange(ctx, state)
	}
}

func (m *ClientConnmanager) GetConn() *grpc.ClientConn {
	m.mu.Lock()
	defer m.mu.Unlock()
	return m.conn
}

func (m *ClientConnmanager) Isready() error {
	m.mu.Lock()
	defer m.mu.Unlock()
	if m.conn == nil {
		return fmt.Errorf("client connection is not ready")
	}
	return nil
}
