<template>
    <div class="pie">
        <div id="pie1">
            <!-- 为 echartsNew 准备一个具备大小（宽高）的 DOM -->
            <div id="main1" style="float:left;width:100%;height: 300px"></div>
        </div>
        <div id="pie2">
            <div id="main2" style="float:left;width:100%;height: 300px"></div>
        </div>
    </div>
</template>
 
<script>
    var echartsNew = require('echarts');
 
    export default {
        props:{
            dateArray:Array,
        },
        data() {
            return {
                // 数据
                data1: [],
                data:[],
                legends:[],
            }
        },
        watch:{
          dateArray(newValue){
              this.data1 = []
              this.data = []
              this.legends = []
              this.data1 = newValue
              this.initData();
          }
        },
        mounted(){
            this.initData
        },
        methods: {
            initData() {
                const param = this.data1
                for (let index = 0; index < param.length; index++){
                    var queryparam = param[index].split("-") 
                    // console.log(param[index])
                    console.log(param[index].split("-"))
                    var requestbody = {year:2020,
                                        month:queryparam[0],
                                        day:queryparam[1]}
                    
                    this.$axios.post('/api/manager/peopleInThatDay',JSON.stringify(requestbody),{
                        headers: {
                        "Content-Type": "application/json",
                        },}).then(resp =>{
                            if (!isNaN(resp.data.data)) {
                                // console.log(resp.data.data)
                                let number = resp.data.data
                                let dataSingle = {value:number,
                                                name:param[index]}
                                this.legends.push(param[index])
                                this.data.push(dataSingle)  
                            }}
                            );
                }
                // console.log(this.data)
                this.requested = true;
                let yearparam = {year:2019}
                this.$axios.post('/api/manager/peopleInThatYear',JSON.stringify(yearparam),{
                    headers: {
                    "Content-Type": "application/json",
                    },}).then(resp => {
                    // 动态数据
                    console.log(resp.data)
                    // 基于准备好的dom，初始化echartsNew实例
                    var myChart1 = echartsNew.init(document.getElementById('main1'));
                    // 绘制图表
                    myChart1.setOption({
                        title : {
                            text: 'RollerCoaster',
                            subtext: 'MockData',
                            x:'center'
                        },
                        tooltip : {
                            trigger: 'item',
                            formatter: "{a} <br/>{b} : {c} ({d}%)"
                        },
                        legend: {
                            orient: 'vertical',
                            bottom: 'bottom',
                            data: this.legends
                        },
                        series : [
                            {
                                name: 'Number of people',
                                type: 'pie',
                                radius : '55%',
                                center: ['50%', '60%'],
                                data: this.data,
                                itemStyle: {
                                    emphasis: {
                                        shadowBlur: 10,
                                        shadowOffsetX: 0,
                                        shadowColor: 'rgba(0, 0, 0, 0.5)'
                                    }
                                }
                            }
                        ]
                    });
                }).finally(() => {
                    this.requested = false;
                });
            },
        }
    }
</script>