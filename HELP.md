mvn clean package
mvn spring-boot:run
docker build -t kuroshan:t1 .
docker run -p 5000:8080 kuroshan:t1

POST
http://localhost:5000/api/auth/signup
{
	"name": "Americo Sierra",
	"username": "americo",
	"email": "americosierra@hotmail.com",
	"password": "123456"
}

POST
http://localhost:5000/api/auth/signin
{
	"usernameOrEmail": "americo",
	"password": "123456"
}

POST
http://localhost:5000/api/notificaciones
Authorization Bearer xxxxxxxxxx
{
	"tipoNotificacion": "email",
	"nombreCliente": "Americo Sierra",
	"otpIdentificacion": 12345,
	"codigoOperacion": 123123123,
	"fechaTransaccion": "2020-02-02T09:24:20.985Z",
	"cabecera": "Mi Titulo",
	"fechaPago": "2012-02-02T09:24:20.985Z"
}

GET
http://localhost:5000/api/notificaciones/mensaje/1
Authorization Bearer xxxxxxxxxx



http://localhost:8080/swagger-ui.html
