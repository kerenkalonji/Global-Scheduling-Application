# Stage 1: Build Angular app
FROM node:18-alpine AS build
WORKDIR /app
COPY package*.json ./
RUN npm install
COPY . .
RUN npm run build --prod

# Stage 2: Serve Angular with NGINX
FROM nginx:alpine
COPY --from=build /app/dist/landon-hotel-app /usr/share/nginx/html
EXPOSE 80
