import http from "../http-common"; 

class JobService {
  getAllJobs(searchDTO) {
    console.log(searchDTO)
    return this.getRequest(`/job/jobs`, searchDTO);
  }

  get(jobId) {
    return this.getRequest(`/job/${jobId}`, null);
  }

  findByField(matchData) {
    return this.getRequest(`/job?field=${matchData}`, null);
  }

  addJob(data) {
    return http.post("/job/addJob", data);
  }

  update(data) {
  	return http.post("/job/updateJob", data);
  }
  
  uploadImage(data,jobId) {
  	return http.postForm("/job/uploadImage/"+jobId, data);
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

export default new JobService();
