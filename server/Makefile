BASE_URL=http://localhost:8080

healthcheck:
	curl $(BASE_URL)/healthcheck

signup: 
	curl -X POST $(BASE_URL)/api/auth/signup \
	-H 'Content-type: application/json' \
	-d '{ "email":"email", "password": "password" }'

login: 
	curl -X POST $(BASE_URL)/api/auth/login \
	-H 'Content-type: application/json' \
	-d '{ "email":"email", "password": "password" }'
