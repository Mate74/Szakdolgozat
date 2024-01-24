import Phaser from 'phaser'

export default class MenuScene extends Phaser.Scene {
	constructor() {
		super('MenuScene')
	}

	preload() {
		this.load.image('background', './images/background.jpg')
		this.load.image('menu','./images/menu.png')
		this.load.image('start', './images/start.png')
		this.load.image('option', './images/option.png')

		let loadingBar = this.add.graphics({
            fillStyle: {
                color: 0x00000
            }
        })

        //LoadingBar
        this.load.on("progress", (percent)=>{
            loadingBar.fillRect(0, this.game.renderer.height / 2, this.game.renderer.width * percent, 50);
        })
	}

	create() {
		this.add.image(0,0, 'background').setDepth(0).setOrigin(0)
		this.add.image(this.game.renderer.height/2,this.game.renderer.width*0.2+100 , 'menu').setDepth(1)
		let play=this.add.image(this.game.renderer.height/2,this.game.renderer.width*0.2 +200, 'start').setDepth(1)
		this.add.image(this.game.renderer.height/2,this.game.renderer.width*0.2 +300, 'option').setDepth(1)	

		play.setInteractive();
		 play.on("pointerdown",()=>{
			this.scene.start('MainScene')
		 })
	}
}
