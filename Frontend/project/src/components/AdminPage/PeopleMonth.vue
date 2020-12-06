<template>
    <div class="pie">
        <div id="pie1">
            <!-- 为 ECharts 准备一个具备大小（宽高）的 DOM -->
            <div id="main1" style="float:left;width:100%;height: 300px"></div>
        </div>
        <div id="pie2">
            <div id="main2" style="float:left;width:100%;height: 300px"></div>
        </div>
    </div>
</template>
 
<script>
    var echarts = require('echarts');
 
    export default {
        props:{
            monthRange:Array,
        },
        data() {
            return {
                // 数据
                data1: [],
                months:[],
                numbers:[],
                legends:[],
            }
        },
        watch:{
          monthRange(){
            this.months = []
            this.numbers = []
            this.legends = []
            this.initData();
          }
        },
        mounted() {
            this.initData();
        },
        methods: {
            //初始化数据
            initData() {
                // console.log("chart",this.monthRange)
                for (let index = Number(this.monthRange[0]); index <= Number(this.monthRange[1]); index++) {
                    this.months.push(index);
                    
                    var param = {year:2020,
                                month:Number(index)}
                    this.$axios.post('/api/manager/peopleInThatMonth',JSON.stringify(param),{
                        headers: {
                        // "Access-Control-Allow-Credentials": "true",
                        "Content-Type": "application/json",
                        },}).then(resp =>{
                            if (!isNaN(resp.data.data)) {
                                let number = resp.data.data
                                // console.log(number,index);
                                // this.numbers.push(number);   
                                let param = {value:number,
                                            name:Number(index) + " Month"}
                                this.legends.push(Number(index) + " Month")
                                this.numbers.push(param)
                            }        
                            } 
                    );
                }
                console.log("legends",this.legends,this.numbers)
                this.requested = true;
                let yearparam = {year:2020}
                this.$axios.post('/api/manager/peopleInThatYear',JSON.stringify(yearparam),{
                        headers: {
                        // "Access-Control-Allow-Credentials": "true",
                        "Content-Type": "application/json",
                        },}).then(resp => {
                    // 动态数据
                    this.data1 = resp.data;
                    // 基于准备好的dom，初始化echarts实例
                    var myChart = echarts.init(document.getElementById('main1'));
                    // 绘制图表
                    myChart.setOption({
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
                                data: this.numbers,
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
