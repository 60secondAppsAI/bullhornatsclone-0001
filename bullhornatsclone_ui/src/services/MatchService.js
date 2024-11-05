import http from "../http-common"; 

class MatchService {
  getAllMatchs(searchDTO) {
    console.log(searchDTO)
    return this.getRequest(`/match/matchs`, searchDTO);
  }

  get(matchId) {
    return this.getRequest(`/match/${matchId}`, null);
  }

  findByField(matchData) {
    return this.getRequest(`/match?field=${matchData}`, null);
  }

  addMatch(data) {
    return http.post("/match/addMatch", data);
  }

  update(data) {
  	return http.post("/match/updateMatch", data);
  }
  
  uploadImage(data,matchId) {
  	return http.postForm("/match/uploadImage/"+matchId, data);
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

export default new MatchService();
