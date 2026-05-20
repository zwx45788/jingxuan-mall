set shell := ["cmd.exe", "/c"]


proto:
    protoc -I=proto \
        --go_out=./api_gateway/api/shopping --go_opt=paths=source_relative \
        --go-grpc_out=./api_gateway/api/shopping --go-grpc_opt=paths=source_relative \
        proto/*.proto
    cd shopping && mvn compile -q