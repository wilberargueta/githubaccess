*****
Archivo de configuracion application.yml
Ejecutar script para la base de datos schemas.sql
Reemplazar en el archivo de configuracion la propiedad "access_ token": ACCESS_TOKE, por el token personal de github.com

---

ENDPOINTS PARA LA CONSULTA DE AUDITORIA
- Permite consultar todos los registros de auditoria.
 ENDPOINT: Firma GET /audit
 
- Permite consultar un registro de auditoria por ID.
 ENDPOINT: Firma GET /audit/{id}