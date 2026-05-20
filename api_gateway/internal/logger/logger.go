package logger

import (
	"os"

	config "shopping/internal/config"

	"go.uber.org/zap"
	"go.uber.org/zap/zapcore"
	"gopkg.in/natefinch/lumberjack.v2"
)

// 全局变量，供其他包调用
var Log *zap.SugaredLogger

func Init(cfg *config.LoggerConfig) {
	// 1. 配置 Lumberjack 用于日志文件切割
	hook := lumberjack.Logger{
		Filename:   cfg.Localpath, // 日志文件路径
		MaxSize:    100,           // 每个日志文件最大尺寸(MB)
		MaxBackups: 30,            // 保留的旧日志文件最大个数
		MaxAge:     7,             // 保留旧日志文件的最大天数
		Compress:   true,          // 是否压缩/归档旧日志文件
	}

	// 2. 配置 Zap 的高亮编码器（给控制台看的）
	consoleEncoder := zapcore.NewConsoleEncoder(zapcore.EncoderConfig{
		TimeKey:        "ts",
		LevelKey:       "level",
		NameKey:        "logger",
		CallerKey:      "caller",
		MessageKey:     "msg",
		StacktraceKey:  "stacktrace",
		LineEnding:     zapcore.DefaultLineEnding,
		EncodeLevel:    zapcore.CapitalColorLevelEncoder, // 带颜色的级别输出
		EncodeTime:     zapcore.ISO8601TimeEncoder,       // ISO8601 时间格式
		EncodeDuration: zapcore.SecondsDurationEncoder,
		EncodeCaller:   zapcore.ShortCallerEncoder,
	})

	// 3. 配置 Zap 的 JSON 编码器（给文件/ELK看的）
	jsonEncoder := zapcore.NewJSONEncoder(zapcore.EncoderConfig{
		TimeKey:        "ts",
		LevelKey:       "level",
		MessageKey:     "msg",
		EncodeLevel:    zapcore.LowercaseLevelEncoder, // 小写级别 info
		EncodeTime:     zapcore.ISO8601TimeEncoder,
		EncodeDuration: zapcore.SecondsDurationEncoder,
		EncodeCaller:   zapcore.ShortCallerEncoder,
	})

	// 4. 创建 Core，将日志同时输出到控制台和文件
	var cores []zapcore.Core
	if cfg.Stdout {
		cores = append(cores, zapcore.NewCore(consoleEncoder, zapcore.Lock(os.Stdout), logLevel(cfg.Level)))
	}
	if cfg.Localout {
		cores = append(cores, zapcore.NewCore(jsonEncoder, zapcore.AddSync(&hook), logLevel(cfg.Level)))
	}

	core := zapcore.NewTee(cores...)
	// 5. 构建Logger，增加调用者信息（打印打日志的文件名和行号）
	logger := zap.New(core, zap.AddCaller(), zap.AddCallerSkip(1))

	// 6. 赋值给全局 SugaredLogger
	Log = logger.Sugar()
}

func logLevel(level string) zapcore.LevelEnabler {
	switch level {
	case "debug":
		return zapcore.DebugLevel
	case "info":
		return zapcore.InfoLevel
	case "warn":
		return zapcore.WarnLevel
	case "error":
		return zapcore.ErrorLevel
	default:
		return zapcore.InfoLevel
	}
}
