package server

import (
	"fmt"
	"net/http"
	"shopping/internal/config"
	"shopping/internal/transport/http/router"
)

type Server struct {
	httpServer *http.Server
}

func New(cfg *config.Config) *Server {
	router := router.New()
	return &Server{

		httpServer: &http.Server{
			Addr:    fmt.Sprintf(":%d", cfg.Server.Port),
			Handler: router,
		},
	}
}

func (s *Server) Start() error {
	return s.httpServer.ListenAndServe()
}
