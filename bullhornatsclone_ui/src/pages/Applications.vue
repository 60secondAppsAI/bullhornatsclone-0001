<template>
  <div class="content">
    <div class="row">
      <div class="col-12">
        <card class="card-plain">
          <!-- <template slot="header">
            <h4 class="card-title">Table on Plain Background</h4>
            <p class="category">Here is a subtitle for this table</p>
          </template>-->
          <div class="table-full-width text-left">
            <application-table
            v-if="applications && applications.length > 0"
				:title="table1.title"
				:sub-title="table1.subTitle"
				:applications="applications"
				:totalElements="totalElements"
				:page="page"
				:columns="table1.columns"
 				@get-all-applications="getAllApplications"
             >

            </application-table>
          </div>
        </card>
      </div>

    </div>
  </div>
</template>
<script>
import { Card } from "@/components/Card";

import ApplicationTable from "@/components/ApplicationTable";
import ApplicationService from "../services/ApplicationService";

const tableColumns = [];
const tableData = [
];

export default {
  components: {
    Card,
    ApplicationTable,
  },
  data() {
    return {
      applications: [],
	  totalElements: 0,
      page: 0,
      searchQuery: '',     
      table1: {
        title: "Simple Table",
        columns: [...tableColumns],
        data: [...tableData],
      },
    };
  },
  methods: {
    async getAllApplications(sortBy='applicationId',sortOrder='true',page="0",size="50") {
      try {
        try {
			const searchDTO = {
				sortBy: sortBy, 
				sortOrder: sortOrder, 
				searchQuery: this.searchQuery,
				page: page, 
				size: size
			};
	        let response = await ApplicationService.getAllApplications(searchDTO);
	        if (!response.data.empty) {
		        if (response.data.applications.length) {
					this.applications = response.data.applications;
				}
      			this.totalElements = response.data.totalElements;
      			this.page = response.data.page;
	        }
        } catch (error) {
          console.error("Error fetching applications:", error);
        }
        
      } catch (error) {
        console.error("Error fetching application details:", error);
      }
    },
  },
  mounted() {
    this.getAllApplications();
  },
  created() {
    this.$root.$on('searchQueryForApplicationsChanged', (searchQuery) => {
    	this.searchQuery = searchQuery;
    	this.getAllApplications();
    })
  }
};
</script>
<style></style>
