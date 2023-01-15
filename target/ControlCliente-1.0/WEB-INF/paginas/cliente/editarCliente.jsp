<%-- 
    Document   : clientes
    Created on : Jan 10, 2023, 5:48:58 PM
    Author     : Fanta
--%>




<!DOCTYPE html>
<html>
    <head>

        <!-- Required meta tags -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <!-- font awesome -->
        <script src="https://kit.fontawesome.com/9e1a1aa56e.js" crossorigin="anonymous"></script>
        <title>Editar Cliente</title>
    </head>
    <body>
        <%--esto es lo de la clase de incluir dimamicamente o estaticamente, en este caso es dinamicamente--%>
        <jsp:include page="/WEB-INF/paginas/comunes/cabezero.jsp"/>

        <%--
        
        ${cliente.idCliente} aqui estamos haciendo referencia a la variable del objecto Cliente que se esta compartiendo
        en el metodo editarCliente ese metodo se encuentra en ServletControlador
        
        --%>
        <form action="${pageContext.request.contextPath}/ServletControlador?action=modificar&idCliente=${cliente.idCliente}"
              method="POST" class="was-validated">

            <!--botones de navegacion-->
            <jsp:include page="/WEB-INF/paginas/comunes/botonesEdicion.jsp"/>

            <section id="details">
                <div class="container">
                    <div class="row">

                        <div class="col">
                            <div class="card">
                                <div class="card-header">
                                    <h4>Editar Cliente</h4>
                                </div>
                                <div class="card-body">
                                    <div class="modal-body">
                                        <div class="form-group">

                                            <label for="nombre">Nombre</label>
                                            <input type="text" class="form-control" name="nombre" required value="${cliente.nombre}"/>
                                        </div>

                                        <div class="form-group">

                                            <label for="apellido">Apellido</label>
                                            <input type="text" class="form-control" name="apellido" required value="${cliente.apellido}"/>
                                        </div>
                                        <div class="form-group">

                                            <label for="email">Email</label>
                                            <input type="email" class="form-control" name="email" required value="${cliente.email}"/>
                                        </div>
                                        <div class="form-group">

                                            <label for="telefono">Telefono</label>
                                            <input type="tel" class="form-control" name="telefono" required value="${cliente.telefono}"/>
                                        </div>
                                        <div class="form-group">

                                            <label for="saldo">Saldo</label>
                                            <%--
                                            step="any" eso significa que ese campo va a acedptar valores desimales y enteros
                                            --%>
                                            <input type="number" class="form-control" name="saldo" required value="${cliente.saldo}" step="any"/>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

                    </div>
                </div>

            </section>


        </form>
        <!--pie de pagina-->
        <jsp:include page="/WEB-INF/paginas/comunes/piePagina.jsp"/>


        <!-- Optional JavaScript -->
        <!-- jQuery first, then Popper.js, then Bootstrap JS -->
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.14.7/dist/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
    </body>
</body>
</html>
