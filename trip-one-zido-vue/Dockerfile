FROM node:20.11
COPY package.json .
ADD . .
RUN npm install
CMD [ "npm", "run", "serve" ]
