import Phaser from 'phaser'
import MainScene from './Scenes/MainScene'
import MenuScene from './Scenes/MenuScene'

const config = {
	type: Phaser.AUTO,
	parent: 'app',
	width: 1875,
	height: 950,

	physics: {
		default: 'matter',
		matter: {
			debug:true,
			gravity: { y: 0 },
		},
	},
	scene: [MenuScene,MainScene],

}

export default new Phaser.Game(config)
