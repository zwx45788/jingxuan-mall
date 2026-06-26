package redis

import (
	"shopping/internal/config"
	"shopping/internal/logger"

	"github.com/redis/go-redis/v9"
)

var rdb *redis.Client

func Init(cfg config.RedisConfig) {
	rdb = redis.NewClient(&redis.Options{
		Addr:     cfg.Addr,
		Password: cfg.Password,
		DB:       cfg.DB,
	})
}

func GetClient() (*redis.Client, error) {
	if rdb == nil {
		logger.Log.Error("Redis客户端未初始化")
		return nil, redis.ErrClosed
	}
	return rdb, nil
}
