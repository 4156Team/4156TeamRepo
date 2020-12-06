<template>
<el-menu  router class="el-menu-demo" mode="horizontal">
  <el-menu-item index="/adminHome">
    Processing Center
  </el-menu-item>
  <el-menu-item index="/adminInfo" >Info</el-menu-item>
  <el-submenu index="2">
    <template slot="title">Workspace</template>
    <el-menu-item index="/adminFacility">Facility</el-menu-item>
    <el-menu-item index="/adminEvent">Event</el-menu-item>
    <el-menu-item index="/adminprice">Ticket&Annoucement</el-menu-item>
  </el-submenu>
  <el-menu-item index="/adminCheckin">>Check In</el-menu-item>
  <el-menu-item index="4" disabled>Info</el-menu-item>
    <el-menu-item index="/" >UserView</el-menu-item>
</el-menu>
</template>