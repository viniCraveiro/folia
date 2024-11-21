#!/bin/bash

IMAGE_NAME="folia-app"
DOCKER_HUB_USER="vinicraveiro"
DOCKER_HUB_REPO="$DOCKER_HUB_USER/$IMAGE_NAME"
TAG="latest"

echo "Fazendo login no Docker Hub..."
docker login || { echo "Falha ao fazer login no Docker Hub"; exit 1; }

echo "Construindo a imagem Docker..."
docker build --platform linux/arm64 -t $DOCKER_HUB_REPO:$TAG .

if [[ $? -ne 0 ]]; then
  echo "Falha na criação da imagem Docker."
  exit 1
fi

echo "Enviando a imagem para o Docker Hub..."
docker push $DOCKER_HUB_REPO:$TAG || { echo "Falha ao enviar a imagem para o Docker Hub"; exit 1; }

echo "Imagem enviada com sucesso: $DOCKER_HUB_REPO:$TAG"
