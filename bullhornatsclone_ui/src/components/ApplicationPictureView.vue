<template> 


    <div>
        <div class="row card-container">
            <div class="col application-card" v-for="app in applications" :key="app.applicationId">
                <a :href="'#application/' +  app.applicationId"  target="_blank" class="application-link"><img class="application-image" :alt="app.applicationNumber" :src="`/images/${app.imageFile}`" /></a>
                <!-- <img class="application-image" :alt="app.applicationNumber"
                    :src="`https://source.unsplash.com/collection/928423/230x250`" /> -->
                <div class="application-info">
                    <div class="application-details">
                        
                        <p>{{ app.applicationTopLong }}</p>
                        <p>{{ app.applicationSidebarLong }}</p>
                        <p>AppName: <a :href="'#application/' +  app.applicationId"  target="_blank" class="application-link">{{ app.applicationName }}</a></p>
                        <p>PackageName:  {{ app.packageName }}</p>
                        <p>DatabaseName:     {{ app.databaseName }}</p>
                        <p>DatabaseType:     {{ app.databaseType }}</p>
                        <p v-if="app.domain != 'no.domain.set'" >Live Site: <a :href="'https://' + app.domain" target="_blank" class="application-link">{{ app.domain }}</a></p>
                        <p v-else>Live Site: {{ app.domain }}</p>
                	</div>
                </div>
            </div>
        </div>
    </div>



</template>
  
<script>

export default {
    components: {
    },
    props: {
        applications: Array,
    },

    methods: {

    },
};
</script>
  
<style scoped>
.card-container {
    display: flex;
    column-gap: 15px;
    flex-wrap: wrap;
    margin: 5px;

}

.card {
    max-width: 230px;

}

.application-picture-view {
    margin-bottom: 15px;
    /* Separate each row from the content below */
}

.application-card {
    max-width: 250px;
    min-width: 250px;
    border-radius: 30px;
    padding: 0px;
    margin-bottom: 25px;
    /* Separate each card */
}

.application-image {
	    max-width: 200px;
	    height: 45px;
        	border-radius: 4px;
    }

.application-info {
    margin-top: 0px;
}

.application-name {
    font-weight: bold;
    font-size: 16px;
    margin-bottom: 2px;
    line-height: 1.2;
}

.application-details p {
    margin-top: 0px;
    text-align: left;
}

.application-link {
    display: inline;
    margin-top: 5px;
    color: #1890ff;
}
</style>
  
