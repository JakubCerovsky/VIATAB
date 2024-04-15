FROM node:18-alpine AS builder
# Set working directory
WORKDIR /frontend
# Copy package.json and package-lock.json
COPY ./package*.json ./
# Install dependencies (only in builder stage)
RUN npm install
# Copy the rest of the application files (excluding node_modules)
COPY . .

# Cache node_modules to improve build speed
FROM node:18-alpine
# Copy the rest of the application files (including node_modules)
COPY --from=builder /frontend/node_modules ./node_modules
COPY --from=builder /frontend/ .

# Expose React app port (default: 3000)
EXPOSE 3000

# Start the development server
CMD [ "npm", "start" ]