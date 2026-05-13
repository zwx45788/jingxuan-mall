package main

import (
	"context"
	"os"
	"os/signal"
	"shopping/internal/config"
	"shopping/internal/logger"
	"shopping/internal/server"
	"syscall"
)

var cfg *config.Config

func init() {
	configPath := "config.yaml"
	err := config.Init(configPath)
	cfg = config.GetConfig()
	if err != nil {
		panic(err)
	}
	logger.Init(&cfg.Logger)
	logger.Log.Info("配置文件从%s加载，日志系统初始化成功", configPath)
}
func main() {

	ctx, stop := signal.NotifyContext(context.Background(), os.Interrupt, syscall.SIGTERM)
	defer stop()
	srv := server.New(cfg)
	serverErr := make(chan error, 1)
	go func() {
		err := srv.Start()
		if err != nil {
			serverErr <- err
		}
	}()
	select {
	case err := <-serverErr:
		stop()
		logger.Log.Fatalf("服务器启动失败: %v", err)
	case <-ctx.Done():
		logger.Log.Info("收到退出信号，正在关闭服务器...")
	}
}
