<template>
  <el-contanier>
    <el-main>
      <QuickpassList />
    </el-main>
  </el-contanier>
</template>
<script>
import QuickpassList from "../components/UserPage/QuickPassList"
export default {
    components:{
        QuickpassList,
    }
}
</script>