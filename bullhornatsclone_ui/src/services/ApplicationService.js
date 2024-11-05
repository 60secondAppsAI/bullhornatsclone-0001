import http from "../http-common"; 

class ApplicationService {
  getAllApplications(searchDTO) {
    console.log(searchDTO)
    return this.getRequest(`/application/applications`, searchDTO);
  }

  get(applicationId) {
    return this.getRequest(`/application/${applicationId}`, null);
  }

  findByField(matchData) {
    return this.getRequest(`/application?field=${matchData}`, null);
  }

  addApplication(data) {
    return http.post("/application/addApplication", data);
  }

  update(data) {
  	return http.post("/application/updateApplication", data);
  }
  
  uploadImage(data,applicationId) {
  	return http.postForm("/application/uploadImage/"+applicationId, data);
  }
  generateCode(applicationId) {
    return this.getRequest(`/application/generateCode/${applicationId}`, null);
  }

  cloneApplication(applicationId) {
    return this.getRequest(`/application/cloneApplication/${applicationId}`, null);
  }




	postRequest(url, data) {
		return http.post(url, data);
      };

	getRequest(url, params) {
        return http.get(url, {
        	params: params
        });
    };

}

export default new ApplicationService();
