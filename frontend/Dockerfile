# From parent directory
#
# docker build -f frontend/Dockerfile -t frontend .
# docker run -it -v .:/app frontend sh
# cd frontend
# eas login
# eas update

FROM node:22-alpine

WORKDIR /app

RUN apk add --no-cache git
RUN npm install --global eas-cli
