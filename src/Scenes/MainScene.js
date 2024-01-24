import Phaser from 'phaser'
import Warrior from '../GameObjects/Warrior';
import GameManager from '../GameObjects/GameManager';
import Entity from '../GameObjects/Entity';


export default class MainScene extends Phaser.Scene {
	constructor() {
		super(MainScene.name)
        let keyA;
        let keyS;
        let keyD;
        let keyW;
        this.gameManager= new GameManager(this);

	}


	preload() {
		this.load.image('background2','./images/woods.png')
        this.load.atlas('orc','./sprites/orc.png','./sprites/orc_atlas.json')
        this.load.animation('orc_anim','./sprites/orc_anim.json')
        this.load.image('tower','./images/towerxd.png')


/* 		let loadingBar = this.add.graphics({
            fillStyle: {
                color: 0x00000
            }
        })

        //LoadingBar
        this.load.on("progress", (percent)=>{
            loadingBar.fillRect(0, this.game.renderer.height / 2, this.game.renderer.width * percent, 50);
        }) */
	}

	create() {
        this.gameManager.initGameManager(this.game.renderer.width/6,this.game.renderer.height/5*4,this.game.renderer.width/6*5,this.game.renderer.height/5*4);
        
		this.add.image(0, 0, 'background2').setDepth(0).setOrigin(0,0)

        
        
	}
    update(){
/*          this.player.anims.play('orc_walk',true);
        const speed=-2.5;
        let playerVelocity= new Phaser.Math.Vector2();
        if(true) {
            console.log('A key pressed')
            playerVelocity.x=-1;
         }
        else if (this.keyD.isDown) {
            console.log('D key pressed')
            playerVelocity.x=1;
            
         }
         playerVelocity.scale(speed)
         this.player.setVelocity(playerVelocity.x,playerVelocity.y)  */

         this.gameManager.teams.forEach((team)=>{
            team.entities.forEach((entity)=>{
                entity.brain();
            })
         })
         
    }
}
