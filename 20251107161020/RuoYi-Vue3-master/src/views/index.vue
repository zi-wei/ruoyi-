<template>
  <div class="app-container home-pro">
    <!-- 1. 顶部视觉焦点区 -->
    <div class="hero-section" :style="{ backgroundImage: 'url(' + currentImage + ')' }">
      <!-- 左右切换按钮 -->
      <button class="hero-nav-btn hero-nav-prev" @click="prevImage">
        <el-icon><ArrowLeft /></el-icon>
      </button>
      <button class="hero-nav-btn hero-nav-next" @click="nextImage">
        <el-icon><ArrowRight /></el-icon>
      </button>
      
      <!-- 轮播指示器 -->
      <div class="hero-indicators">
        <span 
          v-for="(image, index) in images" 
          :key="index"
          class="indicator" 
          :class="{ active: index === currentIndex }"
          @click="setImage(index)"
        ></span>
      </div>
      
      <div class="hero-overlay">
        <h1 class="hero-title">三角洲护航管理中心</h1>
        <p class="hero-subtitle">精英集结，使命必达。您的专属护航平台。</p>
        <el-button type="primary" size="large" round @click="goToMissionBoard">查看任务悬赏</el-button>
      </div>
    </div>

    <!-- 2. 核心业务引导区 -->
    <div class="zone-introduction">
      <el-row :gutter="30">
        <el-col :xs="24" :sm="12">
          <el-card shadow="hover" class="zone-card" @click="goToEscortZone">
            <div class="zone-content">
              <img src="/img/3.jpg" class="zone-icon" alt="护航专区图标"/>
              <div class="zone-text">
                <h3 class="zone-title">护航专区</h3>
                <p class="zone-description">寻求顶尖专家护航，完成高难度任务，保障您的资产安全撤离。</p>
              </div>
            </div>
          </el-card>
        </el-col>
        <el-col :xs="24" :sm="12">
           <el-card shadow="hover" class="zone-card" @click="goToPlayZone">
            <div class="zone-content">
              <img src="/img/2.jpg" class="zone-icon" alt="陪玩专区图标"/>
              <div class="zone-text">
                <h3 class="zone-title">陪玩专区</h3>
                <p class="zone-description">寻找风趣幽默或技术高超的玩伴，享受每一场游戏的乐趣与陪伴。</p>
              </div>
            </div>
          </el-card>
        </el-col>
      </el-row>
    </div>

    <el-divider><el-icon><Promotion /></el-icon></el-divider>

    <!-- 3. 动态情报展示区 -->
    <div class="dynamic-info">
       <el-row :gutter="30">
        <!-- 最新任务情报 -->
        <el-col :xs="24" :lg="16">
          <el-card shadow="hover" class="info-card">
            <template #header>
              <div class="card-title">
                <el-icon><DataLine /></el-icon>
                <span>最新任务情报</span>
              </div>
            </template>
            <el-table :data="latestMissions" style="width: 100%" height="280px" stripe>
              <el-table-column prop="map" label="地图" width="100"></el-table-column>
              <el-table-column prop="objective" label="核心目标" show-overflow-tooltip></el-table-column>
              <el-table-column prop="reward" label="悬赏金额" width="100">
                <template #default="scope">
                  <el-tag type="warning">￥{{ scope.row.reward }}</el-tag>
                </template>
              </el-table-column>
              <el-table-column label="操作" width="80">
                <template #default="scope">
                  <el-button type="primary" link size="small" @click="goToMissionDetail(scope.row)">立即应征</el-button>
                </template>
              </el-table-column>
            </el-table>
          </el-card>
        </el-col>

        <!-- 精英护航员榜 -->
        <el-col :xs="24" :lg="8">
          <el-card shadow="hover" class="info-card">
            <template #header>
              <div class="card-title">
                <el-icon><Trophy /></el-icon>
                <span>精英打手榜</span>
              </div>
            </template>
            <ul class="specialist-list">
              <li v-for="(specialist, index) in topSpecialists" :key="specialist.id" class="specialist-item">
                <div class="rank" :class="`rank-${index + 1}`">{{ index + 1 }}</div>
                <el-avatar :size="40" :src="specialist.avatar" />
                <div class="info">
                  <div class="name">{{ specialist.name }}</div>
                  <div class="stat">成功率: {{ specialist.successRate }}%</div>
                </div>
                <div class="orders">{{ specialist.ordersCompleted }} 单</div>
              </li>
            </ul>
          </el-card>
        </el-col>
      </el-row>
    </div>

  </div>
</template>

<script setup name="Index">
import { ref, onMounted } from 'vue';
import { useRouter } from 'vue-router';
import { DataLine, Trophy, Promotion, ArrowLeft, ArrowRight } from '@element-plus/icons-vue'

const router = useRouter();

// 图片轮播 - 使用public/package里的图片
const images = ['/package/5.jpg', '/package/8.jpg','/package/9.png'];
const currentIndex = ref(0);
const currentImage = ref('/package/5.jpg');

// 初始化
const initImage = () => {
  if (images.length > 0 && currentIndex.value >= 0 && currentIndex.value < images.length) {
    currentImage.value = images[currentIndex.value];
  }
};

// 切换图片函数
const nextImage = () => {
  currentIndex.value = (currentIndex.value + 1) % images.length;
  initImage();
};

const prevImage = () => {
  currentIndex.value = currentIndex.value === 0 ? images.length - 1 : currentIndex.value - 1;
  initImage();
};

const setImage = (index) => {
  if (index >= 0 && index < images.length) {
    currentIndex.value = index;
    initImage();
  }
};

// --- 模拟数据 ---
const latestMissions = ref([]);
const topSpecialists = ref([]);

// --- 路由跳转方法 ---
function goToMissionBoard() {
  // 跳转到护航任务列表页
  router.push('/hanghang/mission'); 
}
function goToMissionDetail(mission) {
  // 跳转到护航任务详情页，传递任务ID
  router.push({ path: '/hanghang/mission', query: { id: mission.id } });
}
function goToEscortZone() {
  // 跳转到护航专区主页或打手列表
  router.push('/hanghang/specialist'); 
}
function goToPlayZone() {
  // 跳转到陪玩专区主页或人才列表
  router.push('/wanwan/talent');
}


// --- 模拟数据获取 ---
// 注意：这里的latestMissions数据应该与护航专区-任务的数据保持同步
// 建议从护航专区-任务的API获取最新任务数据
function fetchLatestMissions() {
   setTimeout(() => {
    latestMissions.value = [
      { id: 1, map: '航天', objective: '安全撤离，并找到彩蛋箱。', reward: 150 },
      { id: 2, map: '巴克什', objective: '需要两个人质安全撤离', reward: 200 },
      { id: 3, map: '航天', objective: '清图，保底300万物资。', reward: 80 },
      { id: 4, map: '长弓溪谷', objective: '寻找金色怀表', reward: 120 },
      { id: 5, map: '监狱', objective: '带我熟悉一下地图就行', reward: 50 },
    ]
  }, 500)
}
function fetchTopSpecialists() {
   // 注意：这里的topSpecialists数据应该与护航专区主页（打手列表）的数据保持同步
   // 建议从护航专区主页的API获取精英护航员数据
   setTimeout(() => {
    topSpecialists.value = [
      { id: 101, name: '笨重de苷甜', avatar: '/img/1.jpg', successRate: 98.5, ordersCompleted: 128 },
      { id: 102, name: '游龙的权', avatar: '/img/7.jpg', successRate: 95.0, ordersCompleted: 99 },
      { id: 103, name: '赛伊德长官', avatar: '/img/4.jpg', successRate: 96.2, ordersCompleted: 85 },
      { id: 110, name: '大坤来了', avatar: '/img/1.jpg', successRate: 99.1, ordersCompleted: 158 },
    ]
  }, 400)
}

// --- 生命周期钩子 ---
onMounted(() => {
  initImage(); // 初始化图片
  fetchLatestMissions();
  fetchTopSpecialists();
});
</script>

<style scoped lang="scss">
.home-pro {
  // 1. 顶部视觉焦点区
  .hero-section {
    position: relative;
    width: 100%;
    height: 350px;
    background-size: cover;
    background-position: center;
    border-radius: 10px;
    overflow: hidden;
    display: flex;
    align-items: center;
    justify-content: center;
    text-align: center;
    color: #fff;
    margin-bottom: 30px;

    // 左右导航按钮
    .hero-nav-btn {
      position: absolute;
      top: 50%;
      transform: translateY(-50%);
      background: rgba(0, 0, 0, 0.5);
      border: none;
      color: white;
      width: 50px;
      height: 50px;
      border-radius: 50%;
      cursor: pointer;
      display: flex;
      align-items: center;
      justify-content: center;
      transition: background 0.3s ease;
      z-index: 3;

      &:hover {
        background: rgba(0, 0, 0, 0.8);
      }

      .el-icon {
        font-size: 20px;
      }
    }

    .hero-nav-prev {
      left: 20px;
    }

    .hero-nav-next {
      right: 20px;
    }

    // 轮播指示器
    .hero-indicators {
      position: absolute;
      bottom: 20px;
      left: 50%;
      transform: translateX(-50%);
      display: flex;
      gap: 10px;
      z-index: 3;

      .indicator {
        width: 12px;
        height: 12px;
        border-radius: 50%;
        background: rgba(255, 255, 255, 0.5);
        cursor: pointer;
        transition: all 0.3s ease;

        &.active {
          background: #fff;
          transform: scale(1.2);
        }

        &:hover {
          background: rgba(255, 255, 255, 0.8);
        }
      }
    }

    .hero-overlay {
      position: absolute;
      top: 0;
      left: 0;
      width: 100%;
      height: 100%;
      background: rgba(0, 0, 0, 0.5);
      z-index: 2;
      padding: 20px;
      display: flex;
      flex-direction: column;
      justify-content: center;
      align-items: center;
    }

    .hero-title {
      font-size: 3rem;
      font-weight: bold;
      margin-bottom: 10px;
      text-shadow: 2px 2px 8px rgba(0, 0, 0, 0.7);
    }

    .hero-subtitle {
      font-size: 1.2rem;
      margin-bottom: 30px;
      text-shadow: 1px 1px 4px rgba(0, 0, 0, 0.7);
    }
  }

  // 2. 核心业务引导区
  .zone-introduction {
    margin-bottom: 20px;
    
    .zone-card {
      border: none;
      transition: all 0.3s ease;
      cursor: pointer;
      border-radius: 8px;

      &:hover {
        transform: translateY(-5px);
        box-shadow: 0 4px 20px rgba(0, 0, 0, 0.1);
      }

      .zone-content {
        display: flex;
        align-items: center;
        padding: 20px;

        .zone-icon {
          width: 60px;
          height: 60px;
          margin-right: 20px;
        }

        .zone-text {
          .zone-title {
            margin: 0 0 5px 0;
            font-size: 1.5rem;
          }
          .zone-description {
            margin: 0;
            font-size: 0.9rem;
            color: #6c757d;
          }
        }
      }
    }
  }

  // 3. 动态情报展示区
  .dynamic-info {
    margin-top: 30px;
    .info-card {
      height: 380px;
      .card-title {
        font-size: 16px;
        font-weight: bold;
        display: flex;
        align-items: center;
        .el-icon {
          margin-right: 8px;
        }
      }
    }

    .specialist-list {
      list-style: none;
      padding: 0;
      margin: 0;
      .specialist-item {
        display: flex;
        align-items: center;
        margin-bottom: 20px;
        .rank {
          width: 20px;
          font-weight: bold;
          font-size: 16px;
          margin-right: 10px;
          &.rank-1 { color: #ffac38; }
          &.rank-2 { color: #a4a9b3; }
          &.rank-3 { color: #d49364; }
        }
        .info {
          flex: 1;
          margin-left: 15px;
          .name {
            font-weight: bold;
          }
          .stat {
            font-size: 12px;
            color: #999;
          }
        }
        .orders {
          font-size: 12px;
          color: #666;
          font-weight: bold;
        }
      }
    }
  }

  // 通用样式覆盖
  :deep(.el-divider__text) {
    font-size: 1.5rem;
    color: #c0c4cc;
  }
}
</style>