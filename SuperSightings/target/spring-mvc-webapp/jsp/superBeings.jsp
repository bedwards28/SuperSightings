<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>H.E.R.O. - Super Beings</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">  
        <link href="${pageContext.request.contextPath}/css/supersightings.css" rel="stylesheet">
    </head>
    <body>
        <div class="container">
            <h1>Hero Education and Relationship Organization</h1>
            <hr/>
            <nav class="navbar navbar-inverse">
                <div class="container-fluid">
                    <ul class="nav navbar-nav">
                        <li><a href="${pageContext.request.contextPath}/">Home</a></li>
                        <li class="active"><a href="${pageContext.request.contextPath}/supers">Super Beings</a></li>
                        <li><a href="${pageContext.request.contextPath}/organizations">Organizations</a></li>
                        <li><a href="${pageContext.request.contextPath}/locations">Locations</a></li>
                        <li><a href="${pageContext.request.contextPath}/sightings">Sightings</a></li>
                    </ul>
                </div>
            </nav>
            <div class="row">
                <div class="col-md-6">
                    <h2>Super Beings</h2>

                    <div class="content-table">
                         <table id="supersTable" class="table table-hover">
                            <tr>
                                <th width="40%">Super Being</th>
                                <th width="40%">Description</th>
                                <th width="10%"></th>
                                <th width="10%"></th>
                            </tr>
                            <c:forEach var="currentSuper" items="${superList}">
                                <tr>
                                    <td>
                                        <a href="superDetails?superId=${currentSuper.superId}">
                                            <c:out value="${currentSuper.name}"/>
                                        </a>
                                    </td>
                                    <td>
                                        <c:out value="${currentSuper.description}"/>
                                    </td>
                                    <td>
                                        <a href="editSuperBeingForm?superId=${currentSuper.superId}">
                                            Edit
                                        </a>
                                    </td>
                                    <td>
                                        <a href="deleteSuperBeing?superId=${currentSuper.superId}">
                                            Delete
                                        </a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </table>
                    </div>
                </div>

                <div class="col-md-6">
                    <h2>Add Super Being</h2>
                    <sf:form class="form-horizontal" role="form" method="POST" action="createSuperBeing" 
                             modelAttribute="superBeing">
                        <div class="form-group">
                            <label for="add-name" class="col-md-4 control-label">Super Name:</label>
                            <div class="col-md-8">
                                <sf:input type="text" class="form-control" id="add-name" name="name" path="name" placeholder="Super Name"/>
                                <sf:errors path="name" cssclas="error"></sf:errors>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="add-description" class="col-md-4 control-label">Description:</label>
                                <div class="col-md-8">
                                <sf:textarea rows="3" cols="46" class="form-control" id="add-description" name="description" 
                                             path="description"/>
                                <sf:errors path="description" cssclass="error"></sf:errors>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="add-identity" class="col-md-4 control-label">Secret Identity:</label>
                                <div class="col-md-8">
                                <sf:input type="text" class="form-control" name="identity" path="identity" placeholder="Secret Identity"/>
                                <sf:errors path="identity" cssclass="error"></sf:errors>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="add-power" class="col-md-4 control-label">Powers:</label>
                                <div class="col-md-8">
                                    <fieldset class="powersCheckbox">
                                    <c:forEach var="currentPower" items="${powerList}">
                                        <div class="form-control">
                                            <c:choose>
                                                <c:when test="${superBeing.powers.contains(currentPower)}">
                                                    <input type="checkbox" name="powers" value="${currentPower.description}" checked/>
                                                </c:when>
                                                <c:otherwise>
                                                    <input type="checkbox" name="powers" value="${currentPower.description}" />
                                                </c:otherwise>
                                            </c:choose>
                                            <label>${currentPower.description}</label>
                                        </div>
                                    </c:forEach>
                                </fieldset>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-offset-4 col-md-8">
                                <input type="submit" class="btn btn-primary" value="Create Super Hero/Villain"/>
                            </div>
                        </div>
                    </sf:form>
                    <sf:form class="form-horizontal" role="form" method="POST" action="createPower" 
                             modelAttribute="power">
                        <div class="form-group">
                            <div class="col-md-offset-4 col-md-5">
                                <input type="text" class="form-control" maxlength="45" name="power-description" path="description" placeholder="Add power to list"/>
                                <sf:errors path="description" cssclass="error"></sf:errors>
                            </div>
                            <div class="col-md-3">
                                <input type="submit" class="btn btn-primary" value="Add Power" />
                            </div>
                        </div>
                    </sf:form>
                </div>
            </div>

        </div>
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

    </body>
</html>

