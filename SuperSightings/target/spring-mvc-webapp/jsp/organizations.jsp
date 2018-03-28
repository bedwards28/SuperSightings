<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Super Sightings</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">        
    </head>
    <body>
        <div class="container">
            <h1>Super Sightings</h1>
            <hr/>
            <nav class="navbar navbar-inverse">
                <div class="container-fluid">
                    <ul class="nav navbar-nav">
                        <li><a href="${pageContext.request.contextPath}/index.jsp">Home</a></li>
                        <li><a href="${pageContext.request.contextPath}/supers">Super Beings</a></li>
                        <li class="active"><a href="${pageContext.request.contextPath}/organizations">Organizations</a></li>
                        <li><a href="${pageContext.request.contextPath}/locations">Locations</a></li>
                        <li><a href="${pageContext.request.contextPath}/sightings">Sightings</a></li>
                    </ul>
                </div>
            </nav>
            <div class="row">
                <div class="col-md-6">
                    <h2>Organizations</h2>
                    <table id="supersTable" class="table table-hover">
                        <tr>
                            <th width="40%">Organization</th>
                            <th width="30%">Location</th>
                            <th width="15%"></th>
                            <th width="15%"></th>
                        </tr>
                        <c:forEach var="currentOrg" items="${orgList}">
                            <tr>
                                <td>
                                    <a href="organizationDetails?organizationId=${currentOrg.organizationId}">
                                        <c:out value="${currentOrg.description}"/>
                                    </a>
                                </td>
                                <td>
                                    <c:out value="${currentOrg.location.city}"/>
                                </td>
                                <td>
                                    <a href="editOrganizationForm?organizationId=${currentOrg.organizationId}">
                                        Edit
                                    </a>
                                </td>
                                <td>
                                    <a href="deleteOrganization?organizationId=${currentOrg.organizationId}">
                                        Delete
                                    </a>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                </div>
                
                <div class="col-md-6">
                    <h2>Add Organization</h2>
                    <form class="form-horizontal" role="form" method="POST" action="createOrganization">
                        <div class="form-group">
                            <label for="add-organization-name" class="col-md-4 control-label">Name:</label>
                            <div class="col-md-8">
                                <input type="text" class="form-control" name="name" placeholder="Organization Name"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="add-description" class="col-md-4 control-label">Description:</label>
                            <div class="col-md-8">
                                <textarea rows="3" cols="46" class="form-control" name="description" placeholder="Description">
                                </textarea>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="add-address-line-1" class="col-md-4 control-label">Address Line 1:</label>
                            <div class="col-md-8">
                                <input type="text" class="form-control" name="addressLine1" placeholder="Address Line 1" />
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="add-address-line-2" class="col-md-4 control-label">Address Line 2:</label>
                            <div class="col-md-8">
                                <input type="text" class="form-control" name="addressLine2" placeholder="Address Line 2" />
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="add-city" class="col-md-4 control-label">City:</label>
                            <div class="col-md-8">
                                <input type="text" class="form-control" name="city" placeholder="City" />
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="add-region" class="col-md-4 control-label">Region:</label>
                            <div class="col-md-8">
                                <input type="text" class="form-control" name="region" placeholder="Region" />
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="add-postal-code" class="col-md-4 control-label">Postal Code:</label>
                            <div class="col-md-8">
                                <input type="text" class="form-control" name="postalCode" placeholder="Postal Code" />
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="add-country" class="col-md-4 control-label">Country:</label>
                            <div class="col-md-8">
                                <input type="text" class="form-control" name="country" placeholder="Country" />
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="add-latitude" class="col-md-4 control-label">Latitude:</label>
                            <div class="col-md-8">
                                <input type="number" max="180" step=".0001" class="form-control" name="latitude" placeholder="Latitude" />
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="add-longitude" class="col-md-4 control-label">Longitude:</label>
                            <div class="col-md-8">
                                <input type="number" max="180" step=".0001" class="form-control" name="longitude" placeholder="Longitude" />
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="add-phone" class="col-md-4 control-label">Phone:</label>
                            <div class="col-md-8">
                                <input type="text" class="form-control" name="phone" placeholder="Phone" />
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="add-email" class="col-md-4 control-label">Email:</label>
                            <div class="col-md-8">
                                <input type="text" class="form-control" name="email" placeholder="Email" />
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-offset-4 col-md-8">
                                <input type="submit" class="btn btn-primary" value="Create Organization"/>
                            </div>
                        </div>
                    </form>
                </div>
            </div>

        </div>
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

    </body>
</html>

