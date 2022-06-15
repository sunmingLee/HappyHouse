<template>
  <div class="content" v-if="maxDeal.dealAmount">
    <div class="container-fluid">
      <div class="row">
        <div class="col-xl-4 col-md-6">
          <stats-card>
            <div slot="header" class="icon-warning">
              <i class="nc-icon nc-istanbul text-warning"></i>
            </div>
            <div slot="content">
              <p class="card-category">거래 최고가액</p>
              <h4 class="card-title">
                {{ maxDeal.aptName }}
                <br />
                {{ maxDeal.dealAmount | aptValueToWon }}원
              </h4>
            </div>
            <div slot="footer">
              <i class="fa fa-refresh"></i>
              <div class="h5">
                {{ maxDeal.dealYear }}년 {{ maxDeal.dealMonth }}월
                {{ maxDeal.dealDay }}일 거래
              </div>
            </div>
          </stats-card>
        </div>

        <div class="col-xl-4 col-md-6">
          <stats-card>
            <div slot="header" class="icon-danger">
              <i class="nc-icon nc-cart-simple text-danger"></i>
            </div>
            <div slot="content">
              <p class="card-category">아파트 실거래량</p>
              <h4 class="card-title">{{ dealCnt | numberToComma }} 회</h4>
            </div>
            <div slot="footer"><i class="fa fa-clock-o"></i></div>
          </stats-card>
        </div>

        <div class="col-xl-4 col-md-6">
          <stats-card>
            <div slot="header" class="icon-info">
              <i class="nc-icon nc-favourite-28 text-primary"></i>
            </div>
            <div slot="content">
              <p class="card-category">거래가 가장 활발한 아파트</p>
              <h4 class="card-title">
                <strong>{{ mostDealApart.dongName }}</strong>
                {{ mostDealApart.aptName }}
                <br />
                {{ mostDealApart.cnt | numberToComma }}회
              </h4>
            </div>
            <div slot="footer">
              <i class="fa fa-refresh"></i>
            </div>
          </stats-card>
        </div>
      </div>
      <div class="row">
        <div class="col-md-12" v-if="lineChart.data.series.length !== 0">
          <chart-card
            :chart-data="lineChart.data"
            :chart-options="lineChart.options"
            :responsive-options="lineChart.responsiveOptions"
          >
            <template slot="header">
              <h4 class="card-title">Top 10 거래량 아파트</h4>
              <p class="card-category">거래량 많은순</p>
            </template>
            <template slot="footer">
              <div class="legend">
                <i class="fa fa-circle">x축: 아파트명 </i>-
                <i class="fa fa-circle"> y축: 거래량</i>
              </div>
              <hr />
              <i class="fa fa-check"></i>
              <a
                href="https://www.data.go.kr/data/15057511/openapi.do"
                target="_blank"
                >국토교통부 아파트매매 실거래</a
              >
            </template>
          </chart-card>
        </div>
      </div>

      <div class="row">
        <div class="col-md-6" v-if="barChart.data.series.length !== 0">
          <chart-card
            :chart-data="barChart.data"
            :chart-options="barChart.options"
            :chart-responsive-options="barChart.responsiveOptions"
            chart-type="Bar"
          >
            <template slot="header">
              <h4 class="card-title">월별 거래량</h4>
              <p class="card-category">1월 ~ 12월</p>
            </template>
            <template slot="footer">
              <div class="legend">
                <i class="fa fa-circle text-info"></i> 월 거래량
              </div>
              <hr />
              <div class="stats">
                <i class="fa fa-check"></i>
                <a
                  href="https://www.data.go.kr/data/15057511/openapi.do"
                  target="_blank"
                  >국토교통부 아파트매매 실거래</a
                >
              </div>
            </template>
          </chart-card>
        </div>

        <div class="col-md-6" v-if="chartData.data.series.length !== 0">
          <chart-card
            :chart-data="chartData.data"
            :chart-options="chartData.options"
            :responsive-options="chartData.responsiveOptions"
            chart-type="Bar"
          >
            <template slot="header">
              <h4 class="card-title">평수별 거래량</h4>
              <p class="card-category">0~90평</p>
            </template>
            <template slot="footer">
              <div class="legend"></div>
              <hr />
              <i class="fa fa-check"></i>
              <a
                href="https://www.data.go.kr/data/15057511/openapi.do"
                target="_blank"
                >국토교통부 아파트매매 실거래</a
              >
            </template>
          </chart-card>
        </div>
      </div>

      <div class="row">
        <div class="col-12">
          <card>
            <template slot="header">
              <h4 class="card-title">뉴스</h4>
              <p class="card-category">네이버 부동산</p>
            </template>
            <b-table hover :items="tableData.items" :fields="tableData.fields">
              <template #cell(index)="data">
                {{ data.index + 1 }}
              </template>
              <template #cell(title)="data">
                <b-link :href="data.item.url" target="_blank">
                  <span class="text-primary">{{ data.item.title }}</span>

                  <span v-html="handleNewLine(data.item.body)"> </span>
                </b-link>
              </template>

              <template #cell(source)="data">
                {{ data.item.source }}
              </template>
              <template #cell(date)="data">
                {{ data.item.date }}
              </template>
            </b-table>
          </card>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import ChartCard from "@/components/Cards/ChartCard.vue";
import StatsCard from "@/components/Cards/StatsCard.vue";
import {
  dealCount,
  dealCountEachMonth,
  maxDeal,
  mostDealApt,
  mostDealAptTopX,
  newsList,
  rankBySquareFeet,
} from "@/api/house";
import { handleNewLine, numberFormat, numberToKorean } from "@/utils/filters";

export default {
  components: {
    ChartCard,
    StatsCard,
  },
  data() {
    return {
      chartData: {
        data: {
          labels: [],
          series: [],
        },
        options: {
          seriesBarDistance: 10,
          reverseData: true,
          horizontalBars: true,
          axisY: {
            offset: 100,
          },
        },
        responsiveOptions: [
          [
            "screen and (max-width: 640px)",
            {
              axisX: {
                labelInterpolationFnc(value) {
                  return value[0];
                },
              },
              axisY: {
                offset: 1000,
              },
            },
          ],
        ],
      },
      editTooltip: "Edit Task",
      deleteTooltip: "Remove",
      lineChart: {
        data: {
          labels: [],
          series: [],
        },
        options: {
          low: 0,
          high: 800,
          showArea: false,
          height: "245px",
          axisX: {
            showGrid: true,
          },
          lineSmooth: true,
          showLine: true,
          showPoint: true,
        },
        responsiveOptions: [
          [
            "screen and (max-width: 640px)",

            {
              axisX: {
                labelInterpolationFnc(value) {
                  return value[0];
                },
              },
            },
          ],
        ],
      },
      barChart: {
        data: {
          labels: [
            "1월",
            "2월",
            "3월",
            "4월",
            "5월",
            "6월",
            "7월",
            "8월",
            "9월",
            "10월",
            "11월",
            "12월",
          ],
          series: [],
        },
        options: {
          seriesBarDistance: 10,
          axisX: {
            showGrid: false,
          },
          height: "245px",
        },
        responsiveOptions: [
          [
            "screen and (max-width: 640px)",
            {
              seriesBarDistance: 5,
              axisX: {
                labelInterpolationFnc(value) {
                  return value[0];
                },
              },
            },
          ],
        ],
      },
      tableData: {
        items: null,
        fields: [
          { key: "index", label: "#" },
          { key: "title", label: "TITLE" },
          { key: "url", thClass: "d-none", tdClass: "d-none" },
          { key: "body", thClass: "d-none", tdClass: "d-none" },
          { key: "source", label: "출처" },
          { key: "date", label: "날짜" },
        ],
      },
      maxDeal: {},
      dealCnt: 0,
      mostDealApart: {},
    };
  },
  created() {
    rankBySquareFeet(
      async (response) => {
        let label = [];
        let data = [];
        response.data.forEach(({ range, numberOfSquarefeet }) => {
          label.push(`${range}평`);
          data.push(numberOfSquarefeet);
        });
        this.chartData.data.series.push(data);
        this.chartData.data.labels = label;
      },
      (error) => {
        console.log(error);
      },
    );
    maxDeal(
      (response) => {
        this.maxDeal = response.data;
        this.maxDeal.dealAmount.replaceAll(",", "");
      },
      (error) => {
        console.log(error);
      },
    );

    dealCount(
      (response) => {
        this.dealCnt = response.data;
      },
      (error) => {
        console.log(error);
      },
    );

    mostDealApt(
      (response) => {
        this.mostDealApart = response.data;
      },
      (error) => {
        console.log(error);
      },
    );

    dealCountEachMonth(
      (response) => {
        let temp = [];
        response.data.forEach(({ count }) => temp.push(count));
        this.barChart.data.series.push(temp);
      },
      (error) => {
        console.log(error);
      },
    );

    mostDealAptTopX(
      10,
      (response) => {
        let label = [];
        let data = [];
        response.data.forEach(({ aptName, cnt }, idx) => {
          label.push(`${idx + 1}. ${aptName}`);
          data.push(cnt);
        });
        this.lineChart.data.labels = label;
        this.lineChart.data.series.push(data);
      },
      (error) => {
        console.log(error);
      },
    );

    newsList(
      (response) => {
        let data = [...response.data];
        this.tableData.items = data;
      },
      (error) => console.log(error),
    );
  },
  filters: {
    aptValueToWon(data) {
      return numberToKorean(parseInt(data.replaceAll(",", "")) * 10000);
    },
    numberToComma(data) {
      return numberFormat(data);
    },
  },
  methods: {
    handleNewLine(data) {
      return handleNewLine(data);
    },
    numberToKorean(data) {
      return numberToKorean(data);
    },
  },
};
</script>
<style></style>
