# --- Stage 1: Build the React Application ---
# Use an official Node.js image as the builder environment
FROM node:18-alpine as builder

# Set the working directory inside the container
WORKDIR /app

# Copy package.json and package-lock.json first to leverage Docker layer caching
COPY package*.json ./

# Install project dependencies
RUN npm install

# Copy the rest of the application source code
COPY . .

# Build the application for production
# This creates a 'build' folder with static files
RUN npm run build


# --- Stage 2: Serve the Application with Nginx ---
# Use a lightweight Nginx image for the final production image
FROM nginx:stable-alpine

# Copy the static files from the 'builder' stage's 'build' folder
# into the default Nginx public HTML directory
COPY --from=builder /app/build /usr/share/nginx/html

# Remove the default Nginx configuration file
RUN rm /etc/nginx/conf.d/default.conf

# Copy our custom Nginx configuration file from the host
# This file must be in the same directory as this Dockerfile
COPY nginx.conf /etc/nginx/conf.d

# Expose port 80 (Nginx's default port)
EXPOSE 80

# The command to start the Nginx server in the foreground
CMD ["nginx", "-g", "daemon off;"]