<template>
  <el-container>
      <el-main>
        <AdminAnnouncement />
        <el-divider></el-divider>
        <AdminTicketPrice />
      </el-main>
  </el-container>
</template>
<script>
import AdminAnnouncement from "../components/AdminPage/AdminAnnouncement"
import AdminTicketPrice from "../components/AdminPage/AdminTicketPrice"
export default {
    name:"AdminTicketAnnounce",
    components:{
        AdminTicketPrice,
        AdminAnnouncement
    }
    
}
</script>