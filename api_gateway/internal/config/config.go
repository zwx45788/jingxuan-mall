package config

import (
	"fmt"
	"os"

	"github.com/goccy/go-yaml"
)

var cfg *Config

type Config struct {
	Server       ServerConfig `yaml:"server"`
	Logger       LoggerConfig `yaml:"logger"`
	ShoppingGRPC GRPCConfig   `yaml:"shopping_grpc"`
	Jwt          JwtConfig    `yaml:"jwt"`
	Oss          OssConfig    `yaml:"oss"`
	Redis        RedisConfig  `yaml:"redis"`
}

type RedisConfig struct {
	Addr     string `yaml:"addr"`
	Password string `yaml:"password"`
	DB       int    `yaml:"db"`
}

type JwtConfig struct {
	Secret string `yaml:"secret"`
}
type ServerConfig struct {
	Port int `yaml:"port"`
}

type LoggerConfig struct {
	Level     string `yaml:"level"`
	Stdout    bool   `yaml:"stdout"`
	Localout  bool   `yaml:"localout"`
	Localpath string `yaml:"localpath"`
}

type GRPCConfig struct {
	ShoppingServiceAddr string `yaml:"shopping_service_addr"`
}

type OssConfig struct {
	Endpoint        string `yaml:"endpoint"`
	AccessKeyId     string `yaml:"access_key_id"`
	AccessKeySecret string `yaml:"access_key_secret"`
}

func Init(configPath string) error {
	data, err := os.ReadFile(configPath)
	if err != nil {
		return fmt.Errorf("failed to read config file %s:%w", configPath, err)
	}
	cfg = &Config{}
	err = yaml.Unmarshal(data, cfg)
	if err != nil {
		return fmt.Errorf("failed to unmarshal config file %s:%w", configPath, err)
	}
	return nil
}

func GetConfig() *Config {
	return cfg
}
