<%--

    Copyright (C) 2014 GIP RECIA http://www.recia.fr
    @Author (C) 2014 Julien Gribonvald - julien.gribonvald@recia.fr

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at
    				http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.

--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:choose>
	<c:when test="${ success == true }">
		<c:forEach items="${ ressources }" var="ressource">
			<h1><c:out value="${ ressource.libelle }"/></h1>
			<p>id : <c:out value="${ ressource.identifiantRessource }"/></p>
			<p>date : <c:out value="${ ressource.dateDeFin }"/></p>
			<p>vignette : <c:out value="${ ressource.vignette }"/></p>
			<p>specimen : <c:out value="${ ressource.specimen }"/></p>
			<p>typos ressource : <c:forEach items="${ ressource.typologieRessource }" var="typologieRessource" varStatus="st">
				<c:out value="${ typologieRessource }"/>
				<c:if test="${ not st.last }">, </c:if>
			</c:forEach></p>
			<p>typos document : <c:forEach items="${ ressource.typologieDocument }" var="typologieDocument" varStatus="st">
				<c:out value="${ typologieDocument }"/>
				<c:if test="${ not st.last }">, </c:if>
			</c:forEach></p>
			<p>niveaux : <c:forEach items="${ ressource.niveau }" var="niveau" varStatus="st">
				<c:out value="${ niveau }"/>
				<c:if test="${ not st.last }">, </c:if>
			</c:forEach></p>
			<p>types pedagogique : <c:forEach items="${ ressource.typePedagogique }" var="typePedagogique" varStatus="st">
				<c:out value="${ typePedagogique }"/>
				<c:if test="${ not st.last }">, </c:if>
			</c:forEach></p>
			<p>disciplines : <c:forEach items="${ ressource.discipline }" var="discipline" varStatus="st">
				<c:out value="${ discipline }"/>
				<c:if test="${ not st.last }">, </c:if>
			</c:forEach></p>
			<p>urlAccesGar : <c:out value="${ ressource.urlAccesGar }"/></p>
			<p>appartenances : <c:forEach items="${ ressource.appartenanceBeans }" var="appartenanceBeans">
				<p><c:out value="${ appartenanceBeans.niveauAppartenance }"/> - <c:out value="${ appartenanceBeans.valeurAppartenance }"/> - <c:out value="${ appartenanceBeans.uai }"/></p>
			</c:forEach></p>
		</c:forEach>
	</c:when>
	<c:otherwise>
	<p>Failed to get ressoources : ${ message }</p>
	</c:otherwise>
</c:choose>
