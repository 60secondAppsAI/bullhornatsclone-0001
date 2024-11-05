<template>
  <div class="app-header">
    <div>
    <router-link class="link" to="/${application.defaultViewAfterSignIn}">

	  <img src="images/drc-velocity.png"
        class="logo"
        alt=""
      />
    </router-link>
    </div>
    <div class="links">
      <nav class="nav">
	        <router-link class="link" to="/jobs">My Jobs</router-link>
            
						        <router-link class="link" to="/signup">Create your art</router-link>
        <router-link class="link" to="/arts-gallery"
          >Browse Gallery</router-link
        >
        <router-link class="link" to="/pricing">Pricing </router-link>
      </nav>
      <div class="avator">
        <img src="../assets/avator.jpeg" alt="avator" />
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "AppHeader",
  props: {
    msg: String,
  },
  methods: {
    handleClick() {
      if (this.$route.name && this.$route.name !== "home") {
        this.$router.push("/");
      }
    },
  },
};
</script>

<style scoped>
.app-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 10px 20px;
  box-shadow: 0px -10px 23px rgba(51, 51, 51, 0.2);
  position: sticky;
  top: 0;
  background: #fff;
}
.logo {
  height: 30px;
  cursor: pointer;
}
.links {
  display: flex;
  align-items: center;
}
.link {
  margin-right: 1rem;
  color: #444;
  text-transform: uppercase;
  text-decoration: none;
  font-size: 14px;
}

.link:hover {
  text-decoration: underline;
  color: #0000ff;
}

.avator img {
  width: 40px;
  height: 40px;
  border-radius: 20px;
  border: 1px solid #ccc;
}
</style>

