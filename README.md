Quotes API
Description

This is a RESTful API built with Spring Boot that provides a random inspirational quote.
It includes IP-based rate limiting: each client can make up to 5 requests per minute.
If the limit is exceeded, the API returns HTTP 429 Too Many Requests with a JSON error message.

API Endpoint

GET /api/quote

Response (success)

{
  "quote": "The only way to do great work is to love what you do. - Steve Jobs"
}


Response (rate limit exceeded)

{
  "error": "Rate limit exceeded. Try again in 52 seconds."
}
How to Run Locally

Clone the repository:

git clone https://github.com/bhavika-31/Quotes.git


Open the project in Spring Tool Suite (STS) or Eclipse.

Make sure you have Java 21 installed.

Run QuotesApplication.java as a Spring Boot Application.

The app will start on http://localhost:8080
Testing the API

Using Curl:

curl http://localhost:8080/api/quote


First 5 requests in a minute → returns a random quote in JSON format.

6th+ request in a minute → returns:

{
  "error": "Rate limit exceeded. Try again in 52 seconds."
}
