# 使用 Node.js 作为基础镜像来构建前端
FROM node:18 AS frontend
WORKDIR /app
COPY ./frontend/package*.json ./
RUN npm install
COPY ./frontend ./frontend
RUN npm run build

# 使用 gradle 作为基础镜像来构建后端
FROM gradle:jdk23 AS gradle
WORKDIR /app
COPY ./backend .
COPY --from=frontend /frontend/dist /app/src/main/resources/static
RUN gradle build

# 使用 OpenJDK 作为基础镜像来运行后端
FROM openjdk:17-jdk-slim AS backend
COPY ./backend/target/food-truck.jar /app.jar
COPY ./run-app.sh  /run-app.sh

ENTRYPOINT exec sh run-app.sh