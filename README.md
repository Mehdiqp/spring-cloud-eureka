# spring-cloud-eureka


senarioi ke darim kheyli simple hast
ma ye eureka server darim ke karesh register kardane instanse ha hastesh
va 2ta service[brand-service & brand-producer] ke ba estefade az eureka server az ham service migiran
inja ziad tozih nemidam code ro bebinid harja ke niaz bud comment gozashtam
 
 vase run kardan 
 
 1- java -jar eureka-service-0.0.1-SNAPSHOT.jar
 2- java -jar brand-service-0.0.1-SNAPSHOT.jar
 3- java -jar brand-producer-0.0.1-SNAPSHOT.jar
 
 hala ke service ha daran kar mikonan
 postman ro baz mikonim o be url localhost:8091/ali ye post request mizanim ba in body
 {
	"name":"NIKE"
 }
 
 age response "ok shod" bud yani inke create successful bud
 
 hala ye get request be url localhost:8091/all
 mizanim o hame Brand ha ro be sorate response migirim
 
 ma ba in kar dg be low level tarin sorate momken 2ta service doros kardim ke be ham service midan
 age doshvari ba code dashtin begin ke begam baraton
