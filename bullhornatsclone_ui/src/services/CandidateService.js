import http from "../http-common"; 

class CandidateService {
  getAllCandidates(searchDTO) {
    console.log(searchDTO)
    return this.getRequest(`/candidate/candidates`, searchDTO);
  }

  get(candidateId) {
    return this.getRequest(`/candidate/${candidateId}`, null);
  }

  findByField(matchData) {
    return this.getRequest(`/candidate?field=${matchData}`, null);
  }

  addCandidate(data) {
    return http.post("/candidate/addCandidate", data);
  }

  update(data) {
  	return http.post("/candidate/updateCandidate", data);
  }
  
  uploadImage(data,candidateId) {
  	return http.postForm("/candidate/uploadImage/"+candidateId, data);
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

export default new CandidateService();
