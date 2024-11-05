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
            <match-table
            v-if="matchs && matchs.length > 0"
				:title="table1.title"
				:sub-title="table1.subTitle"
				:matchs="matchs"
				:totalElements="totalElements"
				:page="page"
				:columns="table1.columns"
 				@get-all-matchs="getAllMatchs"
             >

            </match-table>
          </div>
        </card>
      </div>

    </div>
  </div>
</template>
<script>
import { Card } from "@/components/Card";

import MatchTable from "@/components/MatchTable";
import MatchService from "../services/MatchService";

const tableColumns = [];
const tableData = [
];

export default {
  components: {
    Card,
    MatchTable,
  },
  data() {
    return {
      matchs: [],
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
    async getAllMatchs(sortBy='matchId',sortOrder='true',page="0",size="50") {
      try {
        try {
			const searchDTO = {
				sortBy: sortBy, 
				sortOrder: sortOrder, 
				searchQuery: this.searchQuery,
				page: page, 
				size: size
			};
	        let response = await MatchService.getAllMatchs(searchDTO);
	        if (!response.data.empty) {
		        if (response.data.matchs.length) {
					this.matchs = response.data.matchs;
				}
      			this.totalElements = response.data.totalElements;
      			this.page = response.data.page;
	        }
        } catch (error) {
          console.error("Error fetching matchs:", error);
        }
        
      } catch (error) {
        console.error("Error fetching match details:", error);
      }
    },
  },
  mounted() {
    this.getAllMatchs();
  },
  created() {
    this.$root.$on('searchQueryForMatchsChanged', (searchQuery) => {
    	this.searchQuery = searchQuery;
    	this.getAllMatchs();
    })
  }
};
</script>
<style></style>
